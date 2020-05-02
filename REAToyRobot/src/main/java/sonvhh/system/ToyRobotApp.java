/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonvhh.system;

import sonvhh.reatoyrobot.mvp.MainView;
import sonvhh.reatoyrobot.mvp.Presenter;
import sonvhh.reatoyrobot.mvp.PresenterImpl;
import sonvhh.reatoyrobot.mvp.Robot;
import sonvhh.reatoyrobot.mvp.RobotImpl;
import sonvhh.reatoyrobot.mvp.View;


/**
 *
 * @author HaiSonVH
 */
public class ToyRobotApp {
    public static void main(String[] args) {
        //TODO implement the system
        View view = new MainView();
        Robot model = new RobotImpl();
        Presenter presenter = new PresenterImpl();
        presenter.setModel(model);
        presenter.setView(view);
        
        view.startListen();
    }
}
