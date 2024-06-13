/**
 * @Description
 * @Author everforcc
 * @Date 2023-05-27 22:16
 * Copyright
 */

package cn.cc.dawn.craw.ai9w;

import cn.cc.dawn.craw.ai9w.menu.IMenu;
import cn.cc.dawn.craw.ai9w.menu.MenuImpl;

public class Flow {

    public static void main(String[] args) {
        int i = 0;
        IMenu iMenu = new MenuImpl();
        String c1 = "https://m.ai9w.com/book/11612/1749829.html";
        while (c1.endsWith(".html")) {
            i++;
            // 1. 拿到内容
            String html = iMenu.html(c1);
            // 2. 写入 txt
            String title = iMenu.title(html);
            String content = iMenu.content(html);
            // 3. 取出下一章
            c1 = "https://m.ai9w.com" + iMenu.nextUrl(html);
            iMenu.write(i, title, content);
        }
        System.out.println("结束: " + c1);
    }

}
