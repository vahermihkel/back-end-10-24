import React, { useEffect, useRef, useState } from 'react'

function Komakohaga() {
  const [komakohaga, setKomakohaga] = useState([]);
  const numberRef = useRef();
  const [summa, setSumma] = useState();
  const [sonum, setSonum] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/saa-komakohaga")
      .then(res => res.json())
      .then(json => setKomakohaga(json))
    fetch("http://localhost:8080/sonum")
      .then(res => res.text())
      .then(text => setSonum(text))
  }, []);

  function kustuta(index) {
    fetch("http://localhost:8080/kustuta-komakohaga/" + index, {method: "DELETE"})
      .then(res => res.json())
      .then(json => setKomakohaga(json))
    fetch("http://localhost:8080/sonum")
      .then(res => res.text())
      .then(text => setSonum(text))
  }

  function lisa() {
    fetch("http://localhost:8080/lisa-komakohaga/" + numberRef.current.value, {method: "POST"})
      .then(res => res.json())
      .then(json => setKomakohaga(json))
    fetch("http://localhost:8080/sonum")
      .then(res => res.text())
      .then(text => setSonum(text))
  }

  function arvutaKokku() {
    fetch("http://localhost:8080/arvuta-kokku")
      .then(res => res.json())
      .then(json => setSumma(json))
  }

  function koguarv() {
    fetch("http://localhost:8080/kogus")
      .then(res => res.json())
      .then(json => setSumma(json))
  }

  function tahti(index) {
    fetch("http://localhost:8080/arvuta-tahed/" + index)
      .then(res => res.json())
      .then(json => setSumma(json))
  }

  function esimene(index) {
    fetch("http://localhost:8080/esimene-taht/" + index)
      .then(res => res.json())
      .then(json => setSumma(json))
  }

  return (
    <div>
      <div>{sonum}</div>
      <label>Number</label> <br />
      <input ref={numberRef} type="text" /> <br />
      <button onClick={lisa}>Sisesta</button> <br />
      {komakohaga.map((nr, index) => 
        <div>
          {nr}
          <button onClick={() => kustuta(index)}>x</button>  
          <button onClick={() => tahti(index)}>Näita mitu tähte</button>
          <button onClick={() => esimene(index)}>Näita esimest tähte</button>
        </div>)}

      <button onClick={arvutaKokku}>Arvuta kõik numbrid kokku</button>
      <button onClick={koguarv}>Arvuta numbrite kogus</button>
      <div>{summa}</div>
    </div>
  )
}

export default Komakohaga