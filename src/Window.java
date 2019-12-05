import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window extends JFrame {
    private Bank bank;

    Window() {
        bank = new Bank();
        init();
    }

    private void init() {
        setTitle("转账系统");
        setBounds(600, 300, 400, 400);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container = getContentPane();

        JLabel label1 = new JLabel("转出账号");
        label1.setBounds(50, 50, 80, 30);
        label1.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(label1);

        JLabel label2 = new JLabel("转入账号");
        label2.setBounds(50, 100, 80, 30);
        label2.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(label2);

        JLabel label3 = new JLabel("转账金额");
        label3.setBounds(50, 150, 80, 30);
        label3.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(label3);

        JLabel label4 = new JLabel("转账结果");
        label4.setBounds(50, 200, 80, 30);
        label4.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(label4);

        JTextField jtf1 = new JTextField();
        jtf1.setBounds(160, 50, 180, 30);
        jtf1.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(jtf1);

        JTextField jtf2 = new JTextField();
        jtf2.setBounds(160, 100, 180, 30);
        jtf2.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(jtf2);

        JTextField jtf3 = new JTextField();
        jtf3.setBounds(160, 150, 180, 30);
        jtf3.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(jtf3);

        JLabel label = new JLabel();
        label.setBounds(160, 200, 180, 30);
        label.setFont(new Font("宋体", Font.PLAIN, 18));
        container.add(label);

        JButton btn1 = new JButton("确定");
        btn1.setBounds(80, 280, 80, 30);
        btn1.setFont(new Font("宋体", Font.PLAIN, 15));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String user_out = jtf1.getText();
                String user_in = jtf2.getText();
                int count = Integer.parseInt(jtf3.getText());

                index res = bank.transferMoney(user_out, user_in, count);

                switch (res) {
                    case SUCCESS:
                        label.setText("转账成功");
                        break;
                    case NULL_ACCOUNT:
                        label.setText("账户不存在");
                        break;
                    case NO_MONEY:
                        label.setText("账户金额不足");
                        break;
                    case OTHER:
                        label.setText("转账错误");
                        break;
                }

            }
        });
        container.add(btn1);

        JButton btn2 = new JButton("取消");
        btn2.setBounds(240, 280, 80, 30);
        btn2.setFont(new Font("楷体", Font.PLAIN, 15));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*todo,暂时没有功能*/
                System.exit(0);
            }
        });
        container.add(btn2);

        setVisible(true);
    }
}
