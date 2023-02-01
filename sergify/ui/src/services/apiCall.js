import axios from "axios";
let uri = "http://localhost:8000/api/v1/";

async function getAlbumCollection() {
  const options = {
    method: "GET",
    url: uri + "album",
  };
  const result = await apiCall(options);

  return result;
}

async function getAllTracks(albumId) {
  const options = {
    method: "GET",
    url: uri + `album/${albumId}`,
  };

  const result = await apiCall(options);

  return result;
}

async function apiCall(options) {
  try {
    const response = await axios.request(options);

    return response.data;
  } catch (error) {
    console.error(error);
  }
}

export { getAlbumCollection, getAllTracks };
