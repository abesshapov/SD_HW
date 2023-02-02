package Factories;

import Cellphones.Cellphone;
import Cellphones.CreatableCellphones;

/**
 * Фабрика по созданию телефонов.
 */
public abstract class CellphoneFactory {
    /**
     * Запуск процесса создания телефона выбранной модели.
     * @param cellphoneModel выбранная модель.
     * @return созданный телефон.
     */
    public Cellphone cellphoneCreationProcess(CreatableCellphones cellphoneModel) {
        Cellphone cellphone = createCellphone(cellphoneModel);
        cellphone.collectDetails();
        cellphone.combineDetails();
        System.out.println("Cellphone is ready!");
        return cellphone;
    }

    /**
     * Создание телефона заданной модели.
     * @param cellphoneModel модель.
     * @return созданная модель телефона.
     */
    public abstract Cellphone createCellphone(CreatableCellphones cellphoneModel);
}
