/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author fransergio-dev
 */
public class JsfUtil {
    //Métodos para mostar mensagens na tela

    public static void addMsgSucesso(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, msg);
    }

    public static void addMsgPerigo(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, msg);
    }

    public static void addMsgErro(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, msg);
    }

    public static void addMsgErroFatal(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, mensagem);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, msg);
    }
}
