import React, { useEffect, useState } from 'react'

function Toiduained() {
  const [toiduained, setToiduained] = useState([]);
  const [vastus, setVastus] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/toiduained")
      .then(res => res.json())
      .then(json => setToiduained(json))
  }, []);

  const sorteeriAZ = () => {
    fetch("http://localhost:8080/toiduained-az")
      .then(res => res.json())
      .then(json => setVastus(json))
  }

  const valkeKokku = () => {
    fetch("http://localhost:8080/liida-valgud")
      .then(res => res.json())
      .then(json => setVastus(json))
  }

  // const sysivesikuidKokku = () => {
  //   fetch("http://localhost:8080/liida-sysivesikud")
  //     .then(res => res.json())
  //     .then(json => setVastus(json))
  // }

  const rasvuKokku = () => {
    fetch("http://localhost:8080/liida-rasvad")
      .then(res => res.json())
      .then(json => setVastus(json))
  }

  const rasvuKeskmiselt = () => {
    fetch("http://localhost:8080/keskmine-rasv")
      .then(res => res.json())
      .then(json => setVastus(json))
  }

  // const valkeKeskmiselt = () => {
  //   fetch("http://localhost:8080/keskmine-valk")
  //     .then(res => res.json())
  //     .then(json => setVastus(json))
  // }

  function lisa () {
    const uusToiduaine = {
      "nimi": "Muna", // nimiRef.current.value
      "rasv": 5, // rasvRef.current.value
      "sysivesik": 1,
      "valk": 10
    }
    fetch("http://localhost:8080/lisa-toiduaine", {
      method: "POST", 
      body: JSON.stringify(uusToiduaine),
      headers: {"Content-Type": "application/json"}
    })
      .then(res => res.json())
      .then(json => setToiduained(json))
  }

  return (
    <div>
      <button onClick={valkeKokku}>Näita palju on valke</button>
      <button>Näita palju on süsivesikuid</button>
      <button onClick={rasvuKokku}>Näita palju on rasvu</button>
      <button>Näita palju on valke keskmiselt</button>
      <button>Näita palju on süsivesikuid keskmiselt</button>
      <button onClick={rasvuKeskmiselt}>Näita palju on rasvu keskmiselt</button>
      <div>{vastus}</div>
      <button onClick={sorteeriAZ}>Sorteeri A-Z</button>
      {toiduained.map(toiduaine => 
        <div>
          <div><b>{toiduaine.nimi}</b></div>
          <div>Valk: {toiduaine.valk}</div>
          <div>Süsivesik: {toiduaine.sysivesik}</div>
          <div>Rasv: {toiduaine.rasv}</div>
        </div>
      )}

      <label></label>
      <input type="text" />
      <button onClick={lisa}>Lisa</button>
    </div>
  )
}

export default Toiduained