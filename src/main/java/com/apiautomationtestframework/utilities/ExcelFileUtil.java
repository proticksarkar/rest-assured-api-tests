package com.apiautomationtestframework.utilities;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public class ExcelFileUtil {

    private final String excelFilePath;

    public ExcelFileUtil(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public <T> List<T> readExcelFile(Class<T> classType) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
        return Poiji.fromExcel(new File(excelFilePath), classType, options);
    }

}
