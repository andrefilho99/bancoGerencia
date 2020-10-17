import React from 'react';
import './App.css';
import { BrowserRouter, Route, Switch} from "react-router-dom";
import Home from './components/Home';
import Logo from './assets/Gibeank.png';


function AppRouter() {
  return (
    <div className="App">
      <img src={Logo} width={200} />
      <BrowserRouter>
          <Switch> 
            <Route path="/" component={Home} />
          </Switch>
      </BrowserRouter>
    </div>
  );
}

export default AppRouter;
