import React from 'react';
import './App.css';
import { BrowserRouter, Route, Switch} from "react-router-dom";
import Home from './components/Home';
import ListarContas from './components/ListarContas';
import Depositar from './components/Depositar';
import Debitar from './components/Debitar';
import Transferir from './components/Transferir';
import Saldo from './components/Saldo';
import Logo from './assets/Gibeank.png';


function AppRouter() {
  return (
    <div className="App">
      <img src={Logo} width={200} alt="GibeanK logo" />
      <BrowserRouter>
          <Switch> 
            <Route path="/" exact component={Home} />
            <Route path="/listar-contas" component={ListarContas} />
            <Route path="/depositar" component={Depositar} />
            <Route path="/debitar" component={Debitar} />
            <Route path="/transferir" component={Transferir} />
            <Route path="/saldo" component={Saldo} />
          </Switch>
      </BrowserRouter>
    </div>
  );
}

export default AppRouter;
