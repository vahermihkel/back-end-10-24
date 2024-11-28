import React, { useEffect, useRef, useState } from 'react'

function Omanik() {
  const [omanikud, setOmanikud] = useState([]);
  const nimiRef = useRef();

  useEffect(() => {
    fetch("http://localhost:8080/omanikud")
      .then(res => res.json())
      .then(json => setOmanikud(json))
  }, []);

  function lisa() {
    const lisatavOmanik = {
      "nimi": nimiRef.current.value
    }

    fetch("http://localhost:8080/omanikud", {
      method: "POST", 
      body: JSON.stringify(lisatavOmanik),
      headers: {"Content-Type": "application/json"} 
    })                
      .then(res => res.json())  
      .then(json => setOmanikud(json))
  }

  return (
    <div>
      <label>Nimi</label> <br />
      <input ref={nimiRef} type="text" /> <br />
      <button onClick={lisa}>Lisa</button>
      {omanikud.map(omanik => 
        <div key={omanik.id}>
          <span>{omanik.id}. </span>
          <span>{omanik.nimi}</span>
        </div>)}
    </div>
  )
}

export default Omanik