import { Link, useNavigate } from "react-router-dom";
import InputBooksAction from "../Components/InputBooksAction";
import { API_URL } from "../const";


export default function Register() {
    const navigate = useNavigate()
    // const API_URL = import.meta.env.VITE_API_URL;

    let inputLogin: string;
    let inputEmail: string;
    let inputPassword: string;

    async function buttonAction(inputLogin: string, inputEmail: string, inputPassword: string) {
        const tempBody = {
            "username": inputLogin,
            "email":inputEmail,
            "password":inputPassword
        }
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n")

        const res = await fetch(API_URL + "/auth/signup", {
            method: "POST",
            body: JSON.stringify(tempBody),
            headers: {
                "Content-Type": "application/json"
            }
        })

        if (res.ok) {
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
        }
        // window.location.reload()
    }

    return (
        <>
            <div className="register-div">
                <p className="register-text">Регистрация</p>
                <InputBooksAction inputTitle='Ulmas' placeholderTitle="Логин" inputAcion={(Value) => { inputLogin = Value }} />
                <InputBooksAction inputTitle='Ulmas' placeholderTitle="Почта" inputAcion={(Value) => { inputEmail = Value }} />
                <InputBooksAction inputTitle='Ulmas' placeholderTitle="Пароль" inputAcion={(Value) => { inputPassword = Value }} />
                <button className="default-button-block" onClick={() => buttonAction(inputLogin, inputEmail, inputPassword)}>Зарегестрироваться</button>
                
                <Link className= "text-thirdly text-[20px]" to="/login">Уже есть аккаунт? Авторизоваться</Link>

            </div>
        </>
    )
}

