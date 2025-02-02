import { useEffect, useState } from "react"

export default function User() {
    const [userName, setUserName] = useState<string>()
    const [item, setItem] = useState<string | null>("")

    useEffect(() => {
        setItem(localStorage.getItem("token"))
        fetchContent()
    }, [])

    async function fetchContent() {
        const res = await fetch("http://5.159.102.181:5173/secured/user", {
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        })
        if (res.ok) {
            const json = await res.text()
            setUserName(json)
        }
        console.log(userName)
    }

    return <>
        <div className="flex flex-col gap-1">
            {item !== null ? (
                <p className="userInfo-div">Signed is as: {userName}</p>
            ) : (
                <p className="userInfo-div">UNAUTHORIZED</p>
            )}
        </div>
    </>
}
