package java.DesignerPattern.FacadePattern;
//门面对象
public class ModenPostOffice {
    //被委托的对象
    private ILetterProcess letterProcess = new LetterProcessImpl();

    private Police police = new Police();

    public void sendLetter(String context, String address){
        //帮你写好信
        letterProcess.writerContext(context);
        //帮你写信封
        letterProcess.fillEnvelope(address);
        //警察检查信件
        police.checkLetter(letterProcess);
        //帮你装进信封
        letterProcess.letterInotoEnvelope();
        //帮你邮递信件
        letterProcess.sendLetter();

    }

}
