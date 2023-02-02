package Factories;

import Cellphones.Cellphone;
import Cellphones.CreatableCellphones;
import Cellphones.SamsungA150;
import Cellphones.SamsungS7;

/**
 * Фабрика по созданию телефонов компании самсунг.
 */
public class SamsungFactory extends CellphoneFactory {
    /**
     * Создание телефона заданной модели.
     * @param cellphoneModel модель.
     * @return созданная модель телефона.
     */
    @Override
    public Cellphone createCellphone(CreatableCellphones cellphoneModel) {
        Cellphone createdCellphone = null;
        switch (cellphoneModel) {
            case SAMSUNG_S7:
                createdCellphone = new SamsungS7();
                break;
            case SAMSUNG_A150:
                createdCellphone = new SamsungA150();
                break;
            default:
                System.out.println("Unfortunate mistake with factory choice.");
                break;
        }
        return createdCellphone;
    }
}