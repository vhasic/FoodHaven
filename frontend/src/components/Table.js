import React from "react";
import '../style/AdminPage.css';

const Table = props => {
  debugger;
  const startinIndex = (props.currentPage - 1) * props.itemsPerPage;
  const lastIndex = startinIndex + props.itemsPerPage;

  const pageData = props.data.slice(startinIndex, lastIndex);
  return pageData.map((item, index) => {
    const { firstName, lastName, username, email } = item; //destructuring
    return (
      <tr key={index}>
        <td className="cell"><input type="checkbox" /></td>
        <td className="cell">{firstName}</td>
        <td className="cell">{lastName}</td>
        <td className="cell">{username}</td>
        <td className="cell">{email}</td>
        <td className="cell"><i style={{ textIndent: "80%" }} className="fas fa-trash"></i></td>
      </tr>
    );
  });
};

export default Table;
