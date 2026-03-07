package org.example.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class PageResultVO <T>{
    private Long total;
    private List<T> list;

    private PageResultVO(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public static <T> PageResultVO<T> from(IPage<T> page){
        return new PageResultVO<>(page.getTotal(), page.getRecords());
    }
}
