package zionsaaan.numer0n;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Random;


public class Main {

    private static final int width = 1200;
    private static final int height = 800;
    private static final JFrame mainFrame = new JFrame();
    private static final Container contentPane = mainFrame.getContentPane();
    private static final JLabel clickToStart = new JLabel();
    private static final JLabel title = new JLabel();
    private static final JLabel num = new JLabel();
    private static final JLabel Turn = new JLabel();
    private static final JTextArea Area = new JTextArea();
    private static final Number number = new Number(true, 0, 0, 0);
    private static final JLabel clear = new JLabel();
    private static final JLabel clear2 = new JLabel();
    private static final JLabel clear3 = new JLabel();
    private static final JLabel clear4 = new JLabel();
    private static final JLabel clear5 = new JLabel();
    private static final JLabel clear6 = new JLabel();
    private static final JLabel clear7 = new JLabel();

    private static final JLabel gameover = new JLabel();
    private static final JLabel gameover2 = new JLabel();
    private static final JLabel gameover3 = new JLabel();

    public static final int START = 1;
    public static final int GAME = 2;
    public static final int CLEAR = 3;
    public static final int GAMEOVER = 4;

    private static int type;
    private static Boolean startMenu;
    private static Boolean loop;
    private static int Round;
    private static int number1;
    private static int number2;
    private static int number3;
    private static String[] now;

    public static void main(String[] args) {

        //Mainフレームの用意
        mainFrame.setTitle("数字当てゲーム");
        mainFrame.setSize(width, height);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setBackground(Color.WHITE);

        startMenu = true;
        loop = true;
        Round = 1;
        type = START;
        Frame(type);

        mainFrame.setVisible(true);

        Thread thread = new Thread(){
            @Override
            public void run() {
                //無限ループ
                while(true){

                    //イベントディスパッチスレッドでUIを更新
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            if (type == START) {
                                //スタートボタンが押されたときにループ
                                if (!startMenu && loop) {
                                   //スタート時にタイトルを段々薄暗くする
                                    clickToStart.setVisible(false);

                                    if (title.getForeground().equals(Color.DARK_GRAY)) {
                                        title.setForeground(Color.GRAY);
                                    } else if (title.getForeground().equals(Color.GRAY)) {
                                        title.setForeground(Color.WHITE);
                                    } else if (title.getForeground().equals(Color.WHITE)) {
                                        title.setVisible(false);
                                        //段々色を薄くして最終的にゲーム画面へ移行、ループを止める
                                        Frame(GAME);

                                    }
                                }
                            }
                        }
                    });

                    try {
                        sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        };
        if (loop) {
            thread.start();
        }
    }

    public static void Frame(int type) {

        switch (type) {

            //スタート画面生成
            case START:
                clickToStart.setVisible(true);
                clickToStart.setText(">> CLICK TO START");
                clickToStart.setForeground(Color.GRAY);
                clickToStart.setFont(new Font("Arial Black", Font.BOLD, 30));
                clickToStart.setBounds(400, 460, 400, 30);
                clickToStart.setHorizontalAlignment(JLabel.CENTER);
                clickToStart.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        startMenu = false;

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        clickToStart.setForeground(Color.DARK_GRAY);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        clickToStart.setForeground(Color.GRAY);

                    }
                });

                title.setVisible(true);
                title.setText("数字当てゲーム");
                title.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 100));
                title.setForeground(Color.DARK_GRAY);
                title.setHorizontalAlignment(JLabel.CENTER);

                contentPane.add(clickToStart);
                contentPane.add(title, BorderLayout.CENTER);
                break;

            //ゲーム画面生成
            case GAME:
                loop = false;
                Random random = new Random();
                number1 = 0;
                number2 = 0;
                number3 = 0;

                if (number.isChange()) {
                    number1 = random.nextInt(9);
                    number2 = random.nextInt(9);
                    if (number2 == number1) {
                        number2 = random.nextInt(9);
                        if (number2 == number1) {
                            do {
                                number2 = random.nextInt(9);
                            } while (number2 == number1);
                        }
                    }
                    number3 = random.nextInt(9);
                    if (number3 == number1 || number3 == number2) {
                        number3 = random.nextInt(9);
                        if (number3 == number1 || number3 == number2) {
                            do {
                                number3 = random.nextInt(9);
                            } while (number3 == number1 || (number3 == number2));

                        }
                    }

                    number.setNumber1(number1);
                    number.setNumber2(number2);
                    number.setNumber3(number3);

                    System.out.println("loop: " + loop + " number.isChange():" + number.isChange() + " NUM: " +number1 + number2 + number3);

                    num.setVisible(true);
                    num.setText("? ? ?");
                    num.setFont(new Font("Arial Black", Font.BOLD, 200));
                    num.setHorizontalAlignment(JLabel.CENTER);
                    num.setFocusable(true);

                    Area.setVisible(true);
                    Area.setText("");
                    Area.setFont(new Font("メイリオ", Font.PLAIN, 20));
                    Area.setRows(290);
                    Area.setColumns(500);
                    Area.setEditable(false);
                    Area.setEnabled(false);
                    Area.setDisabledTextColor(Color.BLACK);
                    Area.setBounds(20, 20, 290, 1000);
                    Area.append(" ");

                    Turn.setVisible(true);
                    Turn.setText(Round + "ターン目");
                    Turn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
                    Turn.setForeground(Color.BLACK);
                    Turn.setHorizontalAlignment(JLabel.CENTER);
                    Turn.setBounds(250, 150, 400, 50);

                    contentPane.add(Area);
                    contentPane.add(Turn);
                    contentPane.add(num, BorderLayout.CENTER);

                    KeyListener[] key = mainFrame.getKeyListeners();
                    if (key.length == 0) {

                        mainFrame.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                //数字じゃないか数字かの判定
                                char key = e.getKeyChar();
                                if (e.getKeyCode() == 8) {
                                    String[] now = num.getText().split(" ");
                                    if (!now[1].equals("?")) {
                                        now[1] = "?";
                                        num.setText(now[0] + " ? ?");
                                    } else if (!now[0].equals("?")) {
                                        now[0] = "?";
                                        num.setText("? ? ?");
                                    }
                                } else if (Character.isDigit(key)) {
                                    now = num.getText().split(" ");
                                    if (now[0].equals(String.valueOf(e.getKeyChar())) || now[1].equals(String.valueOf(e.getKeyChar()))) {

                                    } else {
                                        if (now[0].equals("?")) {
                                            num.setText(e.getKeyChar() + " ? ?");
                                        } else if (now[1].equals("?")) {
                                            num.setText(now[0] + " " + e.getKeyChar() + " ?");
                                        } else if (now[2].equals("?")) {
                                            num.setText(now[0] + " " + now[1] + " " + e.getKeyChar());
                                            try {
                                                Thread.sleep(200);

                                            } catch (InterruptedException interruptedException) {
                                                interruptedException.printStackTrace();
                                            }

                                            now[2] = String.valueOf(e.getKeyChar());

                                            boolean n1 = false;
                                            boolean n2 = false;
                                            boolean n3 = false;

                                            int hit = 0;
                                            int blow = 0;

                                            if (now[0].equals(String.valueOf(number1))) {
                                                n1 = true;
                                                hit++;

                                            } else if (("" + number1 + number2 + number3).contains(now[0])) {
                                                blow++;
                                            }

                                            if (now[1].equals(String.valueOf(number2))) {
                                                n2 = true;
                                                hit++;

                                            } else if (("" + number1 + number2 + number3).contains(now[1])) {
                                                blow++;
                                            }

                                            if (now[2].equals(String.valueOf(number3))) {
                                                n3 = true;
                                                hit++;

                                            } else if (("" + number1 + number2 + number3).contains(now[2])) {
                                                blow++;
                                            }

                                            if (n1 && n2 && n3) {
                                                num.setVisible(false);
                                                Area.setVisible(false);
                                                Turn.setVisible(false);
                                                Frame(CLEAR);
                                            }


                                            Area.insert("\n " + now[0] + " " + now[1] + " " + now[2] + "  ( " + hit + " HIT " + blow + " BLOW )", 1 + ((Round - 1) * 25));
                                            Round = Round + 1;
                                            if (Round >= 21) {
                                                num.setVisible(false);
                                                Area.setVisible(false);
                                                Turn.setVisible(false);
                                                Frame(GAMEOVER);
                                            }
                                            Turn.setText(Round + "ターン目");
                                            num.setText("? ? ?");
                                        }
                                    }
                                }
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        });
                    }
                    break;
                }

            //クリア画面生成
            case CLEAR:

                final int[] clearY = {200};
                String rank;
                Color color;
                final int[] rankSize = {0};
                final boolean[] bool = {true};
                final int[] delay = {10};
                final boolean[] loop2 = {true};

                clear.setVisible(true);
                clear.setText("CLEAR!!");
                clear.setFont(new Font("Arial Black", Font.BOLD, 80));
                clear.setForeground(Color.GREEN);
                clear.setHorizontalAlignment(JLabel.CENTER);
                clear.setBounds(100, 200,1000,120);

                if ( Round <= 3) {
                    rank = "S";
                    color = Color.magenta;
                } else if (Round <= 6) {
                    rank = "A";
                    color = Color.RED;
                } else if (Round <= 9) {
                    rank = "B";
                    color = Color.BLUE;
                } else if (Round <= 12) {
                    rank = "C";
                    color = Color.GREEN;
                } else if (Round <= 15) {
                    rank = "D";
                    color = Color.CYAN;
                } else {
                    rank = "E";
                    color = Color.DARK_GRAY;
                }

                clear2.setVisible(true);
                clear2.setText(rank);
                clear2.setFont(new Font("NSimSun", Font.BOLD, rankSize[0]));
                clear2.setForeground(color);
                clear2.setHorizontalAlignment(JLabel.CENTER);
                clear2.setBounds(525,240, 150,150);

                clear3.setText("ランク");
                clear3.setFont(new Font("メイリオ", Font.PLAIN, 40));
                clear3.setHorizontalAlignment(JLabel.CENTER);
                clear3.setForeground(Color.DARK_GRAY);
                clear3.setBounds(525,370,150,120);
                clear3.setVisible(false);

                clear4.setForeground(Color.BLACK);
                clear4.setText("答えは " + number1 + number2 + number3 + " でした");
                clear4.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
                clear4.setHorizontalAlignment(JLabel.CENTER);
                clear4.setBounds(310,500,600,40);
                clear4.setVisible(false);

                clear5.setForeground(Color.RED);
                clear5.setText(String.format("<html>%s<font color='dark gray'>%s</font></html>", Round+"ターン", "でクリアしました"));
                clear5.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
                clear5.setHorizontalAlignment(JLabel.CENTER);
                clear5.setBounds(290,550,600,40);
                clear5.setVisible(false);

                clear6.setText("もう一度プレイする");
                clear6.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));;
                clear6.setForeground(Color.GRAY);
                clear6.setHorizontalAlignment(JLabel.CENTER);
                clear6.setBounds(465,630,270,30);
                clear6.setVisible(false);
                clear6.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                        clear.setVisible(false);
                        clear2.setVisible(false);
                        clear3.setVisible(false);
                        clear4.setVisible(false);
                        clear5.setVisible(false);
                        clear6.setVisible(false);
                        startMenu = false;
                        loop = false;
                        Round = 1;
                        now = null;
                        System.out.println("NEXT GAME");
                        Frame(GAME);

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        clear6.setForeground(Color.DARK_GRAY);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        clear6.setForeground(Color.GRAY);
                    }
                });

                contentPane.add(clear);
                contentPane.add(clear2);
                contentPane.add(clear3);
                contentPane.add(clear4);
                contentPane.add(clear5);
                contentPane.add(clear6);
                contentPane.add(clear7, BorderLayout.CENTER);


                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        //無限ループ
                        while(true){

                            //イベントディスパッチスレッドでUIを更新
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    if (clearY[0] > 100) {
                                        clear.setBounds(100, clearY[0], 1000, 120);
                                        clearY[0] = clearY[0] - 1;
                                    } else if (rankSize[0] < 180){
                                        rankSize[0] = rankSize[0] + 3;
                                        clear2.setFont(new Font("Impact", Font.BOLD, rankSize[0]));
                                        clear2.setBounds(525,240, 150, 150);
                                    } else if (bool[0]) {
                                        clear3.setVisible(true);
                                        bool[0] = false;
                                    } else if (delay[0] < 50) {
                                        delay[0]++;
                                    } else {
                                        if (loop2[0]) {
                                            clear4.setVisible(true);
                                            clear5.setVisible(true);
                                            clear6.setVisible(true);
                                            loop2[0] = false;
                                        }
                                    }
                                }
                            });

                            try {
                                sleep(10);
                            } catch (InterruptedException ignored) {
                            }
                        }
                    }
                };
                if (loop2[0]) {
                    thread.start();
                }
                break;

            case GAMEOVER:
                gameover.setVisible(true);
                gameover.setText("GAMEOVER");
                gameover.setFont(new Font("Arial", Font.BOLD, 110));
                gameover.setForeground(Color.RED);
                gameover.setHorizontalAlignment(JLabel.CENTER);

                gameover2.setVisible(true);
                gameover2.setText("答えは " + number1 + number2 + number3 + " でした!");
                gameover2.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 50));;
                gameover2.setHorizontalAlignment(JLabel.CENTER);
                gameover2.setBounds(110,450,1000,50);

                gameover3.setVisible(true);
                gameover3.setText("もう一度プレイする");
                gameover3.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));;
                gameover3.setForeground(Color.GRAY);
                gameover3.setHorizontalAlignment(JLabel.CENTER);
                gameover3.setBounds(470,600,270,30);

                gameover3.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        gameover.setVisible(false);
                        gameover2.setVisible(false);
                        gameover3.setVisible(false);
                        startMenu = false;
                        loop = true;
                        Round = 1;
                        now = null;
                        number.setChange(true);
                        Frame(GAME);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        gameover3.setForeground(Color.DARK_GRAY);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        gameover3.setForeground(Color.GRAY);
                    }
                });

                contentPane.add(gameover2);
                contentPane.add(gameover3);
                contentPane.add(gameover, BorderLayout.CENTER);


        }
    }
}

