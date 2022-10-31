/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.view;

import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author X
 */
public class EdunovaListModel<T> 
        extends DefaultListModel<T>{

    public EdunovaListModel(List<T> podaci) {
        super();
        addAll(podaci);
    }
}
