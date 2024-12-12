import React, { useState, useEffect, useRef } from "react";
import "./App.css";

function TicketingSystem() {
  const [config, setConfig] = useState({
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0,
  });

    const [currentTickets, setCurrentTickets] = useState(0);
    const [logs, setLogs] = useState([]);
    const [isRunning, setIsRunning] = useState(false);
    const socketRef = useRef(null);


    const handleChange = (e) => {
    const { name, value } = e.target;
    setConfig((prevConfig) => ({
        ...prevConfig,
      [name]: Number(value),
    }));
  };

    const handleSave = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/configure", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(config),
            });
            if (response.ok) {
                 fetchLogs();
                console.log("Configuration saved successfully!");
             } else {
                console.error("Failed to save configuration:", response.statusText);
             }
        } catch (error) {
        console.error("Error saving configuration:", error);
      }
    };


    const fetchLogs = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/logs");
            if (response.ok) {
              const data = await response.json();
              setLogs(data);
           } else {
              console.error("Failed to fetch logs:", response.statusText);
            }
        } catch (error) {
            console.error("Error fetching logs:", error);
      }
  };


    const handleStart = () => {
      setIsRunning(true);
        if (!socketRef.current) {
            socketRef.current = new WebSocket("ws://localhost:8080/ticket-status");

            socketRef.current.onopen = () => {
                console.log("WebSocket connection opened");
                 fetchLogs();

            };

            socketRef.current.onmessage = (event) => {
                console.log("WebSocket message received:", event.data);
                fetchLogs();
                // Update any other UI components as needed
            };

            socketRef.current.onerror = (error) => {
                console.error("WebSocket error:", error);
            };

            socketRef.current.onclose = () => {
                console.log("WebSocket connection closed");
                socketRef.current = null; // Clear the socket ref when the connection is closed
            };

        } else {
            console.log("websocket already connected");
           fetchLogs();

       }
    };

    const handleStop = () => {
        setIsRunning(false);
        if (socketRef.current) {
            socketRef.current.close();
            socketRef.current = null;

        }
    };


    useEffect(() => {
        let logInterval;
        if (isRunning) {
            fetchLogs();
        logInterval = setInterval(fetchLogs,1000);

        }
        return () => {
                clearInterval(logInterval)

        };
    }, [isRunning]);



  return (
    <div className="container">
      <div className="config-box">
        <h3>System Configuration</h3>
        <div>
          <label>Total Tickets:</label>
          <input
            type="number"
            name="totalTickets"
            value={config.totalTickets}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Ticket Release Rate (tickets per second):</label>
          <input
            type="number"
            name="ticketReleaseRate"
            value={config.ticketReleaseRate}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Customer Retrieval Rate (tickets per second):</label>
          <input
            type="number"
            name="customerRetrievalRate"
            value={config.customerRetrievalRate}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Maximum Ticket Capacity:</label>
          <input
            type="number"
            name="maxTicketCapacity"
            value={config.maxTicketCapacity}
            onChange={handleChange}
          />
        </div>
        <button onClick={handleSave}>Save Configuration</button>
      </div>

      <div>
        <h3>Logs</h3>
        <div className="logs-container">
          {logs.length > 0 ? (
            logs.map((log, index) => <div key={index}>{log}</div>)
          ) : (
            <div>No logs available</div>
          )}
        </div>
      </div>

        <div className="buttons-container">
          <button className="start-button" onClick={handleStart}>
            Start
          </button>
          <button className="stop-button" onClick={handleStop}>
            Stop
          </button>
        </div>
      </div>
  );
}

export default TicketingSystem;