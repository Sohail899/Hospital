import React, { useEffect, useState } from 'react'
import axios from 'axios';

const BASEURL = 'http://localhost:8082/doctors';
function DoctorList() {
    const [doctors,setDoctors] = useState([]);

    useEffect( ()=>{
        const fetchDoctors = async ()=>{
            const response = await axios.get(`${BASEURL}`);
            setDoctors(response.data);
        }
        fetchDoctors();
        console.log(doctors);
    },[]);
  return (
    <>
    <div>DoctorList</div>
    {/* <input type="text" placeholder='Search Doctor...' value={doctors}/> */}
    <button>Search</button>
    <ol>
        {doctors.map((doctor)=>(
            <div key={doctor.doctorId}>
                <p>{doctor.doctorFirstName}</p>
                <p>{doctor.doctorLasttName}</p>
                <p>{doctor.doctorLastName}</p>
                <p>{doctor.doctorSpecialization}</p>
                <p>{doctor.doctorEmail}</p>
                <p>{doctor.phone}</p>
            </div>
        ))}
    </ol>
    </>
  )
}

export default DoctorList