package com.roaddb.paper.result;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roaddb.paper.model.Category;
import com.roaddb.paper.repository.CategoryRepository;
import com.roaddb.paper.util.Tool;

@RestController
public class ResultApi {


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Tool tool;


    @GetMapping(value = "delRootCate")
    public void delCateRoot() {
        List<Category> c = categoryRepository.findByParentId(1L);
        c.forEach(category -> {
            categoryRepository.delete(category.getChildren());
        });
        System.out.println("finished....");
    }

    @GetMapping(value = "delCate")
    public void delCat() {
        List<Category> c = categoryRepository.findByParentId(-1L);
        List<Category> child = categoryRepository.findByParentId(c.get(0).getId());
        categoryRepository.delete(child.get(0));
    }

    @GetMapping(value = "putRootCate")
    public void cateRoot() {
        Category c = new Category(-1L,"a1", "a1");
        Category res =  categoryRepository.save(c);
        System.out.println(res.toString());
    }

    @GetMapping(value = "putCate")
    public void cate() {
        Category parentRes = categoryRepository.findByParentId(1L).get(0);
        Category c1 = new Category(parentRes.getId(),"b1", "b1");
        Category c2 = new Category(parentRes.getId(),"b2", "b2");
        Category c3 = new Category(parentRes.getId(),"b3", "b3");

        Category res1 =  categoryRepository.save(c1);
        Category res2 =  categoryRepository.save(c2);
        Category res3 =  categoryRepository.save(c3);
    }

    @GetMapping(value = "putCate1")
    public void cate1() {
        Category parentRes = categoryRepository.findByParentId(1L).get(1);
        Category c1 = new Category(parentRes.getId(),"c1", "c1");
        Category c2 = new Category(parentRes.getId(),"c2", "c2");
        Category c3 = new Category(parentRes.getId(),"c3", "c3");

        Category res1 =  categoryRepository.save(c1);
        Category res2 =  categoryRepository.save(c2);
        Category res3 =  categoryRepository.save(c3);

    }


    @GetMapping(value = "getCate")
    public void getC() throws Exception {
        Category parentRes = categoryRepository.findByParentId(-1L).get(0);
        List<Category> cs = parentRes.getChildren();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(cs);
        System.out.println(s);
        System.out.println("------------------------");
        cs.forEach(category -> {
            System.out.println(category.toString());
        });
        System.out.println("------------------------");
        System.out.println(parentRes.toString());
    }


    /**
     *
     *  upload.
     */
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {

        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字

        //文件存放路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\vue-manage-system-master\\static\\uploadimg\\";

        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
        try {
            Tool.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }

        // 返回图片的存放路径
        return filePath;
    }


}
