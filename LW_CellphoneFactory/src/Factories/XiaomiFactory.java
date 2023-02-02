package Factories;

import Cellphones.Cellphone;
import Cellphones.CreatableCellphones;
import Cellphones.XiaomiMi5;
import Cellphones.XiaomiU10;

/**
 * Фабрика по созданию телефонов компании сяоми.
 */
public class XiaomiFactory extends CellphoneFactory {
    /**
     * Создание телефона заданной модели.
     * @param cellphoneModel модель.
     * @return созданная модель телефона.
     */
    @Override
    public Cellphone createCellphone(CreatableCellphones cellphoneModel) {
        Cellphone createdCellphone = null;
        switch (cellphoneModel) {
            case XIAOMI_MI5:
                createdCellphone = new XiaomiMi5();
                break;
            case XIAOMI_U10:
                createdCellphone = new XiaomiU10();
                break;
            default:
                System.out.println("Unfortunate mistake with factory choice.");
                break;
        }
        return createdCellphone;
    }
}