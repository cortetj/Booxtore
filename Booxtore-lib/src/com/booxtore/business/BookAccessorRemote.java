/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author netbean
 */
@Remote
public interface BookAccessorRemote {
    ArrayList getBooksByCategory(int category, int index);
    
}
