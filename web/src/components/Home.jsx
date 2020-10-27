import React from 'react';
import {Link} from 'react-router-dom'
import axios from 'axios';

const PATH = "http://localhost:8080/banco-gerencia/contas";
const Home = () => {

    const criarConta = () => {
        axios.get(`${PATH}/create`).then(({data}) => {
			alert(`Conta de número ${data} criada com sucesso`)
		}).catch((err) => console.log(err));
    }

    const options = [
        {link: 'listar-contas', opt: 'Contas ativas'},
        {link: 'depositar', opt: 'Depósito em conta'},
        {link: 'debitar', opt: 'Debitar de conta'},
        {link: 'transferir', opt: 'Transferência entre contas'},
        {link: 'saldo', opt: 'Verificar saldo'},
    ]

    return( 
        <div className="bank-menu">
            <div className="menu-opt" onClick={() => criarConta()}>
                <div>Criar conta</div>
            </div>
            {options.map((el, i) => {
                return (
                    <Link className="menu-opt" to={`/${el.link}`} key={i}>
                        <div>{el.opt}</div>
                    </Link>
                )
            })}
        </div>
    )
}

export default Home;