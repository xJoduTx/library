import axios from "axios";
import InputBooksAction from "./InputBooksAction"

async function buttonAction(inputTitle: number) {
    await axios.delete("http://localhost:9090/books/delete/" + inputTitle)
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

