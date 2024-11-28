import React from 'react'
import { useEffect, useRef, useState } from 'react';

function Auto() {
  const [autod, setAutod] = useState([]);
  const markRef = useRef();
  const pikkusRef = useRef();
  const massRef = useRef();
  const aastaRef = useRef();
  const omanikuIdRef = useRef();

  useEffect(() => {
    fetch("http://localhost:8080/autod")
      .then(res => res.json())
      .then(json => setAutod(json))
  }, []);

  function lisa() {
    const lisatavAuto = {
      "mark": markRef.current.value,
      "pikkus": pikkusRef.current.value,
      "mass": massRef.current.value,
      "aasta": aastaRef.current.value,
      "omanik": {"id": omanikuIdRef.current.value}
    }

    fetch("http://localhost:8080/autod", {
      method: "POST", 
      body: JSON.stringify(lisatavAuto),
      headers: {"Content-Type": "application/json"} // kui valesti, siis 415 - Unsupported Media Type
    })                 // see rida konventeerib JSON kujule ja kui ei õnnestu, siis jätab stringi
      .then(res => res.json())   // kui pole body't, pole ka headers'd vaja
      .then(json => window.location.reload())
  }

  // const kustuta = (autoId) =>
  function kustuta(autoId) {
    fetch("http://localhost:8080/autod/" + autoId, {
      method: "DELETE"
    })              
      .then(res => res.json())
      .then(json => setAutod(json))
  }

  return (
    <div>
      <label>Mark</label> <br />
      <input ref={markRef} type="text" /> <br />
      <label>Pikkus</label> <br />
      <input ref={pikkusRef} type="number" /> <br />
      <label>Mass</label> <br />
      <input ref={massRef} type="number" /> <br />
      <label>Aasta</label> <br />
      <input ref={aastaRef} type="number" /> <br />
      <label>Omaniku ID</label> <br />
      <input ref={omanikuIdRef} type="number" /> <br />
      <button onClick={lisa}>Lisa</button>

      {autod.map(auto => 
        <div key={auto.id}>
          <div>{auto.mark}</div>
          <div>{auto.pikkus}</div>
          <div>{auto.mass}</div>
          <div>{auto.aasta}</div>
          {/* ? --> kui lähen punktiga edasi järgmisi asju küsima, 
                    aga on võimalus, et seda pole olemas */}
          <div>Omanik: {auto.omanik?.nimi}</div>
          <button onClick={() => kustuta(auto.id)}>Kustuta</button>
        </div>)}

    </div>
  )
}

export default Auto