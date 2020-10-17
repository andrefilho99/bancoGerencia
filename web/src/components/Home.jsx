import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom'


const Home = () => {

    const options = [
        {link: 'criar-conta', opt: 'Criar conta'},
        {link: 'contas-ativas', opt: 'Contas ativas'},
        {link: 'creditar', opt: 'Creditar em conta'},
        {link: 'debitar', opt: 'Debitar de conta'},
        {link: 'transferir', opt: 'Transferência entre contas'},
        {link: 'saldo', opt: 'Verificar saldo'},
    ]

    return( 
        <div className="bank-menu">
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