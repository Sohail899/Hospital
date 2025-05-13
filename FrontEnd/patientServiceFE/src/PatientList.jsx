import React, { useEffect, useState } from 'react';
import axios from 'axios';

const BASEURL = 'http://localhost:8081/patients';

function PatientList() {
    const [list, setList] = useState([]);
    const [searchId, setSearchId] = useState('');
    const [patient, setPatient] = useState(null);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        try {
            const response = await axios.get(`${BASEURL}/${searchId}`);
            setPatient(response.data);
            setError('');
        } catch (error) {
            setPatient(null);
            setError('Patient not found');
        }
    };

    useEffect(() => {
        const fetchPatients = async () => {
            try {
                const response = await axios.get(BASEURL);
                setList(response.data);
            } catch (error) {
                console.error('Error fetching patients', error);
            }
        };
        fetchPatients();
    }, []); // Empty dependency array to run the effect once

    return (
        <div style={{ padding: 20 }}>
            <h2>Search Patient by ID</h2>
            <input 
                type="text" 
                placeholder="Search Patient..." 
                value={searchId} 
                onChange={(e) => setSearchId(e.target.value)} 
            />
            <button onClick={handleSearch}>Search</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            {patient && (
                <div>
                    <h3>Patient Details</h3>
                    <p>Name: {patient.patientName}</p>
                    <p>Gender: {patient.patientGender}</p>
                    <p>Age: {patient.patientAge}</p>
                    <p>Address: {patient.patientAddress}</p>
                </div>
            )}

            <br />
            {/* <h3>All Patients</h3>
            <ul>
                {list.map((patient) => (
                    <li key={patient.patientId}>{patient.patientName}</li>
                ))}
            </ul> */}

            <div style={{ padding: '20px' }}>
                <h2>Patient List</h2>
                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '45px' }}>
                    {list.map((patient) => (
                        <div
                            key={patient.patientId}
                            style={{
                                border: '1px solid rgba(224, 224, 224, 0.21)',
                                borderRadius: '12px',
                                padding: '20px',
                                width: '260px',
                                backgroundColor: '#f9f9ff',
                                boxShadow: '0 4px 12px rgba(0, 0, 0, 0.08)',
                                transition: 'transform 0.2s ease-in-out',
                                color: 'black',
                            }}
                        >
                            <h4>{patient.patientName}</h4>
                            <p>Gender: {patient.patientGender}</p>
                            <p>Age: {patient.patientAge}</p>
                            <p>Address: {patient.patientAddress}</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default PatientList;
