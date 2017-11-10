/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.bean;

import br.com.fransergiodev.testeacif.dao.GrupoProdutoDao;
import br.com.fransergiodev.testeacif.domain.GrupoProduto;
import br.com.fransergiodev.testeacif.util.JsfUtil;
import java.sql.SQLException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fransergio-dev
 */
@FacesConverter(value = "GrupoProdutoConverter", forClass = GrupoProduto.class)
public class GrupoProdutoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && value.trim().length() > 0) {
			GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();
                        GrupoProduto grupoProduto = new GrupoProduto();
                        grupoProduto.setId(Long.parseLong(value));
			try {
				return grupoProdutoDao.getById(grupoProduto);
                                
			} catch (ClassNotFoundException | SQLException e) {
				JsfUtil.addMsgErro("Conversion Error!\nNot a valid formaPagamento.");
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
		if (object != null && (object instanceof GrupoProduto)) {
			GrupoProduto grupoProduto = (GrupoProduto) object;
			/// return categoria.getDescricao().toString();
			return String.valueOf(grupoProduto.getId());
		} else {
			return null;
		}
	}

}
