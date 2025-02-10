import { useNavigate } from "react-router-dom";
import InputBooksAction from "../Components/InputBooksAction";
import { API_URL } from "../const";



export default function Login() {
    const navigate = useNavigate()
    // const API_URL = import.meta.env.VITE_API_URL;
    let inputLogin: string;
    let inputPassword: string;

    async function handle(inputLogin: string, inputPassword: string) {
        const tempBody = {
            "username": inputLogin,
            "password": inputPassword
        }
        localStorage.clear()
        const res = await fetch(API_URL+ "/auth/signin", {
            method: "POST",
            body: JSON.stringify(tempBody),
            headers: {
                "Content-Type": "application/json"
            }
        })
        if (res.ok) {
            const json = await res.text()
            // console.log(json)
            localStorage.setItem("token", json)
            navigate("/")
        }
        window.location.reload()
    }

    return (
        <>
            <div className="login-div">
                <p className="login-text" >Авторизация</p>
                <InputBooksAction inputTitle='Ulmas' placeholderTitle="Логин" inputAcion={(Value) => { inputLogin = Value }} />
                <InputBooksAction inputTitle='Ulmas' placeholderTitle="Пароль" inputAcion={(Value) => { inputPassword = Value }} />
                <button className="default-button-block" onClick={() => {
                    handle(inputLogin, inputPassword)
                }}>Вход</button>
            </div >
        </>
    )
}
