import { API_URL } from "../const"
import InputBooksAction from "./InputBooksAction"

async function buttonAction(inputTitle: number) {
    // const API_URL = import.meta.env.VITE_API_URL;

    const path = API_URL + "/books/delete/" + inputTitle
    await fetch(path,
        {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }
    )
    window.location.reload()
}

export default function deleteBookWindow() {

    let inputTitle = 0

    return (
        <>
            <InputBooksAction inputTitle='Ulmas' placeholderTitle="Id" inputAcion={(Value) => { inputTitle = Value }} />
            <button className="default-button-block" onClick={() => buttonAction(inputTitle)}>Удалить</button>
        </>
    )
}

