package com.data.datawaytest.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.data.datawaytest.common.Result;
import com.data.datawaytest.common.ResultGenerator;
import com.data.datawaytest.common.utils.IdUtil;
import com.data.datawaytest.common.utils.PDFToImgUtil;
import com.data.datawaytest.entity.ImageVO;
import com.data.datawaytest.entity.MyPage;
import com.data.datawaytest.entity.MyWife;
import com.data.datawaytest.entity.MyWifeVO;
import com.data.datawaytest.exceptionhandler.FindException;
import com.data.datawaytest.mappings.WifeCover;
import com.data.datawaytest.services.WifeService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/** @author Baijl
 * @date 2020-04-20
 * @time 10:40
 * @description
 */
@RestController
public class WifeController {
    @Autowired
    private WifeService wifeService;
    @PostMapping("/GET/getWife")
    public Result getWife(@RequestBody @Validated MyWifeVO wife){
        System.out.println(wife);

        return ResultGenerator.genSuccessResult(wife);
    }

    @GetMapping("GET/wife")
    public Result getWife(){
        MyWife myWife = new MyWife("老婆", "邹城市九龙小区", "邹城市实验中学", 20, "13853119765@qq.com");
        boolean save = wifeService.save(myWife);
        if(save){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("插入失败");
        }
    }

    /**
     * 获取所有记录
     * @return
     */
    @GetMapping("GET/all")
    public Result getList() {
        List<MyWife> list = wifeService.list();
        if(!list.isEmpty()){
           return ResultGenerator.genSuccessResult(list);
        }else {
           return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     * 增加记录
     * @param wife
     * @return
     */
    @PostMapping("POST/wife")
    public Result addWife(@RequestBody MyWife wife){
        wife.setId(IdUtil.getId());
        if(wifeService.save(wife)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("插入失败");
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/GET/{id}")
    public Result getWife(@PathVariable("id") int id){
        MyWife byId = wifeService.getById(id);
        if(byId != null){
           return ResultGenerator.genSuccessResult(byId);
        }else {
           throw new IllegalArgumentException("查找失败");
        }
    }


    /**
     * 多条件模糊查询（代码版）
     * @param wife
     * @return
     */
    @PostMapping("/GET")
    public Result getOneByInput(@RequestBody MyWife wife) throws FindException {

        List<MyWife> oneByInput = wifeService.getOneByInput(wife);
        if(!oneByInput.isEmpty()){
            System.out.println(1/0);
            return ResultGenerator.genSuccessResult(oneByInput);
        }else {
            throw new FindException("查找错误");
        }
    }

    /**
     * 多条件查询（手写SQL）
     * @param wife
     * @return
     */
    @PostMapping("/GET/ones")
    public Result getByAll(@RequestBody MyWife wife){
        List<MyWife> byAll = wifeService.getByAll(wife);
        if(!byAll.isEmpty()){
            return ResultGenerator.genSuccessResult(byAll);
        }else {
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     * 多条件分页查询
     * @param wife
     * @param page
     * @return
     */
    @PostMapping("/GET/page")
    public Result getWife(@RequestBody MyWifeVO wife, MyPage page){
        System.out.println(page);
        System.out.println(wife);
        Page<MyWife> myWifePage = new Page<>(wife.getPageIndex(),wife.getPageSize());
        MyWife myWife = WifeCover.INSTANCE.toCoverMyWifeV0(wife);
        List<MyWife> myWives = wifeService.selectByPage(myWifePage, myWife);
        System.out.println(myWife);
        return ResultGenerator.genSuccessResult(myWives);
    }

    /**
     * 批量删除
     * @param myWifeVOList
     * @return
     */
    @PostMapping("/DELETE/one")
    public Result deleteOne(@RequestBody List<MyWifeVO> myWifeVOList){
        System.out.println(myWifeVOList);
        List<MyWife> myWife = WifeCover.INSTANCE.toCoverMyWifeV0List(myWifeVOList);
        System.out.println(myWife);
       if( wifeService.deleteOne(myWife)>0){
           return ResultGenerator.genSuccessResult();
       }else {
           return ResultGenerator.genFailResult("删除失败");
       }
    }

    @PostMapping("/UPDATE/one")
    public Result updateOne(@RequestBody MyWifeVO myWifeVO){
        MyWife myWife = WifeCover.INSTANCE.toCoverMyWifeV0(myWifeVO);
        if(wifeService.saveOrU(myWife)>0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("更新失败");
        }
    }

    @PostMapping("/get")
    public void get(@RequestBody ImageVO imageVO , HttpServletResponse response) throws IOException {

        String url = "https://www.nbcolt.com/oitFile/20191227/549a905f-e43f-4080-850b-b0366a7ca15d.jpg";
        //String url =imageVO.getUrl();
            URL urlConet = new URL(url);
            HttpURLConnection con = (HttpURLConnection)urlConet.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(4 * 1000);
            InputStream inStream = con .getInputStream();    //通过输入流获取图片数据
            response.reset();
            response.setContentType("image/jpeg");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."))+ ".jpg" + "\"");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len2;
            try {
                while ((len2 = inStream.read(b)) > 0){
                    response.getOutputStream().write(b, 0, len2);
                }

                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @GetMapping("/pdf")
    public  String pdf2Image(String PdfFilePath, String dstImgFolder, int dpi) {
         PdfFilePath = "https://www.nbcolt.com/oitFile/20191227/549a905f-e43f-4080-850b-b0366a7ca15d.jpg";

        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = dstImgFolder;

            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /* dpi越大转换后越清晰，相对转换速度越慢 */
            StringBuffer imgFilePath = null;
            for (int i = 1; i < 2; i++) {
                String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                imgFilePath = new StringBuffer();
                imgFilePath.append(imgFilePathPrefix);
                imgFilePath.append(".png");
                File dstFile = new File(imgFilePath.toString());
                BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                ImageIO.write(image, "png", dstFile);
            }
            System.out.println("PDF文档转PNG图片成功！");
            return imgFilePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/png")
    public void get1(HttpServletResponse response,@RequestBody ImageVO imageVO) throws IOException {
      //String  PdfFilePath = "https://www.nbcolt.com/oitFile/20200427/37a480b8-8496-4915-9ab0-ebfd53919f7e.pdf";

        OutputStream stream = response.getOutputStream();
        PDFToImgUtil.PDFToImg(stream,imageVO.getUrl(),0,"jpg");
    }


}

