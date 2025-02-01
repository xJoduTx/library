import axios from "axios"
import { useEffect, useState } from "react"

export default function GetAllBooks(key: string) {
    const [books, setBooks] = useState<ApiData[]>([])

    const getBookApi = async () => {
        try {
            const responce = await fetch('http://localhost:9090/books/all', {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            })
            if (responce.ok){
                setBooks(await responce.json())
            }
        } catch (e) {
            console.log("Невозможно забрать данные из базы данных: " + e)
        }
    }

    useEffect(() => {
        getBookApi()
    }, [])

    return books;
}

interface ApiData {
    key: number,
    id: number,
    title: string,
    author: string,
    isbn: number,
    books_copies: number,
    available_copies: boolean
}

