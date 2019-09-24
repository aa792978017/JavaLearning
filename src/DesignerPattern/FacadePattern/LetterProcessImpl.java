package DesignerPattern.FacadePattern;

//子系统
public class LetterProcessImpl implements ILetterProcess{
    @Override
    public void writerContext(String context) {
        System.out.println("填写信的内容：" + context);
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人的地址姓名" + address);
    }

    @Override
    public void letterInotoEnvelope() {
        System.out.println("把信放入信封里面...");
    }

    @Override
    public void sendLetter() {
        System.out.println("邮递信件...");
    }
}
