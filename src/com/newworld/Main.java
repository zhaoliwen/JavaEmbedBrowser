package com.newworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
/**
 * @author zhaoliwen
 * @date 20190927
 * https://blog.csdn.net/ming19951224/article/details/81157919
 */
public class Main extends JPanel {
 
    private JPanel webBrowserPanel;
 
    private JWebBrowser webBrowser;
 
    public Main(String url) {
        super(new BorderLayout());
        webBrowserPanel = new JPanel(new BorderLayout());
        webBrowser = new JWebBrowser();
        webBrowser.navigate(url);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setMenuBarVisible(false);
        webBrowser.setBarsVisible(false);
        webBrowser.setStatusBarVisible(false);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        //执行Js代码
        //webBrowser.executeJavascript("alert('hello swing')");
    }
 
 
    /**
     * 在swing里内嵌浏览器
     * @param url  要访问的url
     * @param title    窗体的标题
     */
    public  static void  openForm(final String url,final String title){
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                //设置窗体关闭的时候不关闭应用程序
                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension dimension = kit.getScreenSize();
                frame.getContentPane().add(new Main(url), BorderLayout.CENTER);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setBounds(0, 0, dimension.width, dimension.height);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
                //让窗体可见
                frame.setVisible(true);
                //重置窗体大小
                frame.setResizable(true);
                //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                // 设置窗体的宽度、高度
                //frame.setSize(1400, 700);
                // 设置窗体居中显示
                frame.setLocationRelativeTo(frame.getOwner());
            }
        });
        NativeInterface.runEventPump();
    }
 
    public static void main(String[] args) {
        openForm("http://192.168.1.150/zhihuichuxing","zlw");
    }
}