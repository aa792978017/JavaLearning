package DesignerPattern.FacadePattern;

//接口
public interface ILetterProcess {

    //写信
    public void writerContext(String context);

    //写信封
    public void fillEnvelope(String address);

    //把信放入信封
    public void letterInotoEnvelope();

    //然后邮递
    public void sendLetter();
}
