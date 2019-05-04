package fei.controller.excelController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import fei.CustomerServices;
import fei.excel.DownloadExcel;
import fei.model.CustomerModel;
import fei.utils.BasePage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xiaoshijiu
 * @description 下载导出Excel
 * @package fei.controller.excelController
 * @date 2019/5/4
 */
@RestController
public class ExcelController {

    @Reference
    private CustomerServices customerServices;

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        //查询所有员工
        BasePage basePage = new BasePage();
        basePage.setPageNum(1);
        basePage.setPageSize(10);
        PageInfo<CustomerModel> allCustomer = customerServices.getAllCustomer(basePage);
        List<CustomerModel> list = allCustomer.getList();

        //表格标题，就是模型的属性名
        String[] titles = {"顾客ID", "顾客姓名", "顾客职业", "顾客电话", "顾客邮箱"};

        //将list集合数据变成String类型二维的数组，行数+列数
        String[][] datas = new String[list.size()][titles.length];
        for (int i = 0, size = list.size(); i < size; i++) {
            //赋值
            datas[i][0] = String.valueOf(list.get(i).getCustId());
            datas[i][1] = list.get(i).getCustName();
            datas[i][2] = list.get(i).getCustProfession();
            datas[i][3] = list.get(i).getCustPhone();
            datas[i][4] = list.get(i).getCustEmail();
        }

        //表格sheet名
        String sheetName = "员工信息表";

        //调用common下的Excel导出方法，导出Excel
        HSSFWorkbook workbook = DownloadExcel.downloadExcel(sheetName, titles, datas);

        try {
            //文件名，需要编码成ISO8859-1
            String fileName = new String("员工信息表.xls".getBytes("UTF-8"), "ISO8859-1");
            //设置响应头，返回前端下载文件
            response.setHeader("content-Disposition", "attachment;filename=" + fileName);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
