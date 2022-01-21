package com.example.kakaocafe.domain.post.page;

import com.example.kakaocafe.core.meta.PageConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageBtn {
    private String value;
    private String link;
    private boolean isSelected;

    public static PageBtn generatePrevBtn(int pageIndex) {
        return new PageBtn(PageConfig.prev, "/posts?page=" + pageIndex, false);
    }

    public static PageBtn generateOrderBtn(int pageIndex, int selectedIndex) {
        return new PageBtn(String.valueOf(pageIndex), "/posts?page=" + pageIndex, pageIndex == selectedIndex);
    }

    public static PageBtn generateNextBtn(int pageIndex) {
        return new PageBtn(PageConfig.next, "/posts?page=" + pageIndex, false);
    }
}
