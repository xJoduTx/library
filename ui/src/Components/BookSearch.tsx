import axios from "axios"
import InputBooksAction from "./InputBooksAction"

async function buttonSearchAction(inputTitle: number) {
    const API_URL = import.meta.env.VITE_API_URL;

    await axios.get(API_URL + "/books/getByTitle/" + inputTitle)
    window.location.reload()
}

async function buttonTakeAction(inputTitle: number) {
    const API_URL = import.meta.env.VITE_API_URL;
    
    const path = API_URL + "/books/take/" + inputTitle
    await fetch(path, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })

    window.location.reload()

}

export default function findBookWindow() {

    let inputTitle = 0

    return (
        <>
            <InputBooksAction inputTitle='Ulmas' placeholderTitle="Название" inputAcion={(Value) => { inputTitle = Value }} />
            <div className="flex place-content-center">
                <button className="default-button-block" onClick={() => buttonSearchAction(inputTitle)}>Найти</button>
                <button className="default-button-block" onClick={() => buttonTakeAction(inputTitle)}>Забрать</button>
            </div>
        </>
    )
}

