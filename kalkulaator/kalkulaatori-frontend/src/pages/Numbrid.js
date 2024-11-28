import { useEffect, useRef, useState } from 'react';


function Numbrid() {
  const [numbrid, setNumbrid] = useState([]);
  const numberRef = useRef();
  const [kogusumma, setKogusumma] = useState();

  const nr1Ref = useRef();
  const nr2Ref = useRef();
  const [tulemus, setTulemus] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/numbrid")
      .then(res => res.json())
      .then(json => setNumbrid(json))
  }, []);

  function lisaNr10() {
    fetch("http://localhost:8080/lisa-number/10")
      .then(res => res.json())
      .then(json => setNumbrid(json))
  }

  function kustuta(index) {
    fetch("http://localhost:8080/kustuta-number/" + index)
    .then(res => res.json())
    .then(json => setNumbrid(json))
  }

  function lisa() {
    fetch("http://localhost:8080/lisa-number/" + numberRef.current.value)
      .then(res => res.json())
      .then(json => setNumbrid(json))
  }

  function saaKogusumma() {
    fetch("http://localhost:8080/kogusumma")
      .then(res => res.json())
      .then(json => setKogusumma(json))
  }

  function liida() {
    fetch("http://localhost:8080/liida/" + nr1Ref.current.value + "/" + nr2Ref.current.value)
      .then(res => res.json())
      .then(json => setTulemus(json))
  }

  function jaga() {
    fetch("http://localhost:8080/jaga/" + nr1Ref.current.value + "/" + nr2Ref.current.value)
      .then(res => res.json())
      .then(json => setTulemus(json))
  }

  return (
    <div>
       <button onClick={lisaNr10}>Lisa juurde nr 10</button>
      <br /><br />
      <label>Uus number</label> <br />
      <input ref={numberRef} type="text" /> <br />
      <button onClick={lisa}>Lisa</button><br /> <br />
      {numbrid.map((number, index) => 
        <div>
          {number}
          <button onClick={() => kustuta(index)}>x</button>
        </div>)}

        <br /><br />
        <button onClick={saaKogusumma}>Arvuta kogusumma</button>
        <div>{kogusumma}</div>

        <br /><br />

        <input ref={nr1Ref} type="text" />
        <input ref={nr2Ref} type="text" />
        <br />
        <button onClick={liida}>Liida</button>
        <button onClick={jaga}>Jaga</button>
        <div>{tulemus}</div>
    </div>
  )
}

export default Numbrid