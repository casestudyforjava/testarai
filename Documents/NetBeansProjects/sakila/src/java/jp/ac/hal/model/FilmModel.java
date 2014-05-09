/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.hal.model;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import jp.ac.hal.ejb.FilmFacade;
import jp.ac.hal.ejb.LanguageFacade;
import jp.ac.hal.entity.Film;
import jp.ac.hal.entity.Language;


/**
 *
 * @author ryouhei
 */
@Named(value = "filmModel")
@RequestScoped
public class FilmModel {

    /**
     * ビジネスロジック（インジェクトされる）
     */
    @EJB
    protected FilmFacade filmFacade;
    
    @EJB
    protected LanguageFacade languageFacade;
    
    //ppt漏れ？
    protected Film film = new Film();
    


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
    
    public String createFilm() {
        filmFacade.create(getFilm());
        return "filmlist?faces-redirect=true";
    }


    public FilmModel() {
    }
    
    /**
     * 映画の一覧を返す
     * 
     * @return 
     */
    public List<Film> getFilmList() {
        return filmFacade.findAll();
    }
    
    /**
     * 言語取得メソッド
     * @return 
     */
    public SelectItem[] getLangageItems() {
        List<Language> entities = languageFacade.findAll();
        SelectItem[] items = new SelectItem[entities.size() + 1];
        int i = 0;
        items[0] = new SelectItem("", "---");
        i++;
        for (Language l : entities) {
            items[i++] = new SelectItem(l.getLanguageId(), l.getName());
        }
        return items;
    }

}
