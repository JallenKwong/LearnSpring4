# Spring JDBC #

## DAO中使用JdbcTemplate ##

	@Repository
	public class ForumDao {
	
		private JdbcTemplate jdbcTemplate;
	
	
		@Autowired
		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}
	...

配置文件

	<beans ...>
	<context:component-scan base-package="com.smart"/>
	
	<context:property-placeholder location="classpath:jdbc.properties" />
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close"
		p:driverClassName="${jdbc.driverClassName}"
		p:url="${jdbc.url}"
		p:username="${jdbc.username}"
		p:password="${jdbc.password}" />

	<bean id="jdbcTemplate" 
	      class="org.springframework.jdbc.core.JdbcTemplate"
	      p:dataSource-ref="dataSource"/>

	...

## 基本的数据操作 ##

### 更改数据 ###

	public void addForum(final Forum forum) {
		final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES(?,?)";
		Object[] params = new Object[] { forum.getForumName(),
				forum.getForumDesc() };
		// 方式1
		// jdbcTemplate.update(sql, params);

		// 方式2
		// jdbcTemplate.update(sql, params,new
		// int[]{Types.VARCHAR,Types.VARCHAR});

		// 方式3
		/*
		 * jdbcTemplate.update(sql, new PreparedStatementSetter() { public void
		 * setValues(PreparedStatement ps) throws SQLException { ps.setString(1,
		 * forum.getForumName()); ps.setString(2, forum.getForumDesc()); } });
		 */

		// 方式4
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, forum.getForumName());
				ps.setString(2, forum.getForumDesc());
				return ps;
			}
		}, keyHolder);
		forum.setForumId(keyHolder.getKey().intValue());
	}

尽量使用可绑定参数的SQL语句，以便数据库可以复用SQL的执行计划，提高数据库的执行效率。此外，应尽量在DAO中使用类级别的静态常量final static定义SQL字符串，不应在方法内部声明SQL字符串变量，以提高JVM的内存使用效率

在实际运用中，应优先考虑使用不带回调接口的JdbcTemplate方法。首先，回调使代码显得臃肿；其次，回调并不能带来额外的好处。


### 返回数据库的表自增主键值 ###

	KeyHolder keyHolder = new GeneratedKeyHolder();

	jdbcTemplate.update(new PreparedStatementCreator() {
		public PreparedStatement createPreparedStatement(Connection conn)
				throws SQLException {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, forum.getForumName());
			ps.setString(2, forum.getForumDesc());
			return ps;
		}
	}, keyHolder);
	forum.setForumId(keyHolder.getKey().intValue());

### 批量更改数据 ###

	public void addForums(final List<Forum> forums) {
		final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES(?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return forums.size();
			}

			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
				Forum forum = forums.get(index);
				ps.setString(1, forum.getForumName());
				ps.setString(2, forum.getForumDesc());
			}
		});
	}

### 查询数据 ###

- RowCallbackHandler
- RowMapper<T&gt;


	public List<Forum> getForums(final int fromId, final int toId) {
		String sql = "SELECT forum_id,forum_name,forum_desc FROM t_forum WHERE forum_id between ? and ?";
		// 方法1：使用RowCallbackHandler接口
		/*
		 * final List<Forum> forums = new ArrayList<Forum>();
		 * jdbcTemplate.query(sql,new Object[]{fromId,toId},new
		 * RowCallbackHandler(){ public void processRow(ResultSet rs) throws
		 * SQLException { Forum forum = new Forum();
		 * forum.setForumId(rs.getInt("forum_id"));
		 * forum.setForumName(rs.getString("forum_name"));
		 * forum.setForumDesc(rs.getString("forum_desc")); forums.add(forum);
		 * }}); return forums;
		 */

		return jdbcTemplate.query(sql, new Object[] { fromId, toId },
				new RowMapper<Forum>() {
					public Forum mapRow(ResultSet rs, int index)
							throws SQLException {
						Forum forum = new Forum();
						forum.setForumId(rs.getInt("forum_id"));
						forum.setForumName(rs.getString("forum_name"));
						forum.setForumDesc(rs.getString("forum_desc"));
						return forum;
					}
				});
	}

### 查询单值数据 ###

- queryForInt
- queryForLong
- queryForObject


	public double getReplyRate(int userId) {
		String sql = "SELECT topic_replies,topic_views FROM t_topic WHERE user_id=?";
		double rate = jdbcTemplate.queryForObject(sql, new Object[] { userId },
				new RowMapper<Double>() {
					public Double mapRow(ResultSet rs, int index)
							throws SQLException {
						int replies = rs.getInt("topic_replies");
						int views = rs.getInt("topic_views");
						if (views > 0)
							return new Double((double) replies / views);
						else
							return new Double(0.0);
					}
				});
		return rate;
	};

SQL良好编写习惯

- 大写编写关键字和函数
- 小写编写表明、字段等非语意

避免过度切换

1. 先用小写字母写完SQL
2. 用IDE快捷键转换大小写（如Eclipse的Ctrl+Shift+Y或Ctrl+Shift+X）

### 调用存储过程 ###

	public int getUserTopicNum(final int userId) {
		String sql = "{call P_GET_TOPIC_NUM(?,?)}";
		//方式1
		/*Integer num = jdbcTemplate.execute(sql,
				new CallableStatementCallback<Integer>() {
					public Integer doInCallableStatement(CallableStatement cs)
							throws SQLException, DataAccessException {
						cs.setInt(1, userId);
						cs.registerOutParameter(2, Types.INTEGER);
						cs.execute();
						return cs.getInt(2);
					}
				});
		return num;*/
		
		//方式2
		CallableStatementCreatorFactory fac = new CallableStatementCreatorFactory(sql); 
		fac.addParameter(new SqlParameter("userId",Types.INTEGER)); 
		fac.addParameter(new SqlOutParameter("topicNum",Types.INTEGER)); 
		Map<String,Integer> paramsMap = new HashMap<String,Integer>();
		paramsMap.put("userId",userId);
		CallableStatementCreator csc = fac.newCallableStatementCreator (paramsMap);
	
		Integer num = jdbcTemplate.execute(csc,new CallableStatementCallback<Integer>(){
			public Integer doInCallableStatement(CallableStatement cs) 
				throws SQLException, DataAccessException {
				cs.execute();
				return cs.getInt(2);
			}	
		});
	    return num;
	}

## BLOB/CLOB类型数据的操作 ##


## 自增键和行集 ##


## 其他类型的JdbcTemplate ##

- NamedParameterJdbcTemplate
- SimpleJdbcTemplate(已过期)

## 以OO方式访问数据库 ##
