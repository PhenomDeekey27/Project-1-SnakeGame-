import javax.swing.JFrame;
public class Frame extends JFrame
{
    Frame()
    {
        this.setTitle("SnakeGame");
        this.add(new panel());
        this.setVisible(true);
        this.setResizable(false);
        //setresizable helps to maintain the fixed size of the window.
        this.pack();
        //this.pack gives the authority to windows to manage the panel
    }

}
