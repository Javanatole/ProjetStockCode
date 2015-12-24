/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.entity.handler;

import controller.jpacontroller.SessionJpaController;
import java.util.HashMap;
import modele.entity.Session;
import modele.entity.SessionTransactions;
import modele.entity.Transaction;
import modele.entity.factory.EntityFactory;

/**
 *
 * @author yo
 */
public class SessionHelper {
    private Session aCurrentSession;
    
    public SessionHelper(){
        
    }
    
    public boolean connect(String pseudo, String password){
        Session session = SessionJpaController.getController().findByConnexion(pseudo, password);
        if(session == null) return false;
        aCurrentSession = session;
        return true;
    }
    
    public boolean addTransaction(Transaction transaction){
        HashMap<String,Object> map = new HashMap<>();
        map.put("session", aCurrentSession);
        map.put("transaction", transaction);
        SessionTransactions sessionTransactions = EntityFactory.create(new SessionTransactions(), map);
        return sessionTransactions != null;
    }
}
