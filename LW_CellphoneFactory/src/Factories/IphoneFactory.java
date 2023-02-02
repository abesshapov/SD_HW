package Factories;

import Cellphones.Cellphone;
import Cellphones.CreatableCellphones;
import Cellphones.Iphone11;
import Cellphones.IphoneSE;

/**
 * Фабрика по созданию телефонов компании айфон.
 */
public class IphoneFactory extends CellphoneFactory {
    /**
     * Создание телефона заданной модели.
     * @param cellphoneModel модель.
     * @return созданная модель телефона.
     */
    @Override
    public Cellphone createCellphone(CreatableCellphones cellphoneModel) {
        Cellphone createdCellphone = null;
        switch (cellphoneModel) {
            case IPHONE_11:
                createdCellphone = new Iphone11();
                break;
            case IPHONE_SE:
                createdCellphone = new IphoneSE();
                break;
            default:
                System.out.println("Unfortunate mistake with factory choice.");
                break;
        }
        return createdCellphone;
    }
}