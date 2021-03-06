package org.fkit.pagingandsortingrepositorytest.controller;
import java.util.List;

import javax.annotation.Resource;

import org.fkit.pagingandsortingrepositorytest.bean.Article;
import org.fkit.pagingandsortingrepositorytest.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/article")
public class ArticleController {

	@Resource
	private ArticleService articleService;

	@RequestMapping("/sort")
	public Iterable<Article> sortArticle() {
		// 指定排序参数对象：根据id，进行降序查询
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Iterable<Article> articleDatas = articleService.findAllSort(sort);
		return articleDatas;
	}

	@RequestMapping("/page/{pageIndex}")
	public List<Article> sortPagerArticle(@PathVariable int pageIndex) {
		// 指定排序参数对象：根据createDate，进行降序查询
		Sort sort = new Sort(Sort.Direction.DESC, "createDate");
		// 封装分页实体
		// 参数一：pageIndex表示当前查询的第几页(默认从0开始，0表示第一页)
		// 参数二：表示每页展示多少数据，现在设置每页展示2条数据
		// 参数三：封装排序对象，根据该对象的参数指定根据id降序查询
		Pageable page = PageRequest.of(pageIndex - 1, 2, sort);
		Page<Article> articleDatas = articleService.findAll(page);
		// 额外信息
		System.out.println("查询总页数:" + articleDatas.getTotalPages());
		System.out.println("查询总记录数:" + articleDatas.getTotalElements());
		System.out.println("查询当前第几页:" + (articleDatas.getNumber() + 1));
		System.out.println("查询当前页面的记录数:" + articleDatas.getNumberOfElements());

		// 返回查询出的结果数据集合
		return articleDatas.getContent();
	}
}