import Cellphones.CreatableCellphones;
import Factories.CellphoneFactory;
import Factories.IphoneFactory;
import Factories.SamsungFactory;
import Factories.XiaomiFactory;

public class Main {
    public static void main(String[] args) {
        CellphoneFactory iphoneFactory = new IphoneFactory();
        CellphoneFactory samsungFactory = new SamsungFactory();
        CellphoneFactory xiaomiFactory = new XiaomiFactory();
        System.out.println("Xioami Mi5 is ordered:");
        xiaomiFactory.cellphoneCreationProcess(CreatableCellphones.XIAOMI_MI5);
        System.out.println("Samsung S7 is ordered:");
        samsungFactory.cellphoneCreationProcess(CreatableCellphones.SAMSUNG_S7);
        System.out.println("Iphone 11 is ordered:");
        iphoneFactory.cellphoneCreationProcess(CreatableCellphones.IPHONE_11);
    }
}