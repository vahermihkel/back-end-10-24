import React, { useEffect, useState } from 'react'

function Mang() {
  const [auto, setAuto] = useState({});
  const [sonum, setSonum] = useState("");
  const [skoorElud, setSkoorElud] = useState({});

  const votaSkoorJaElud = () => {
    fetch("http://localhost:8080/skoor-ja-elud")
      .then(res => res.json())
      .then(json => setSkoorElud(json))
  };

  useEffect(() => {
    alusta();
  }, []);

  const alusta = () => {
    fetch("http://localhost:8080/alusta-mangu")
      .then(res => res.json())
      .then(json => {
        setAuto(json);
        votaSkoorJaElud();
      })
  }

  const tahti = (guess) => {
    fetch("http://localhost:8080/tahtede-arv/" + guess)
      .then(res => res.text())
      .then(json => setSonum(json))
  }

  const hind = (guess) => {
    fetch("http://localhost:8080/hinna-vordlus/" + guess)
    .then(res => res.text())
    .then(json => setSonum(json))
  }

  const arvaUuesti = () => {
    fetch("http://localhost:8080/arva-uuesti")
      .then(res => res.json())
      .then(json => setAuto(json))
    setSonum("");
    votaSkoorJaElud();
  }

  return (
    <div>
      <div>Sinu skoor: {skoorElud.skoor}</div>
      <div>Sinu elud: {skoorElud.elud}</div>
      <br /><br />
      <div>{auto.nimi}</div>
      <div>{auto.hind}</div>
      {sonum === "" ?
      <>
        <div>Arva kas on suuremad või väiksemad tähed:</div>
        <button onClick={() => tahti("less")}>Vähem</button>
        <button onClick={() => tahti("same")}>Sama palju</button>
        <button onClick={() => tahti("more")}>Rohkem</button>
        <div>Arva kas on suurem või väiksem hind:</div>
        <button onClick={() => hind("less")}>Vähem</button>
        <button onClick={() => hind("same")}>Sama palju</button>
        <button onClick={() => hind("more")}>Rohkem</button>
      </> :
      <button onClick={arvaUuesti}>Arva uuesti</button>
      }
      <div>{sonum}</div>

      {skoorElud.elud === 0 && <button onClick={alusta}>Alusta mängu uuesti</button> }
    </div>
  )
}

export default Mang