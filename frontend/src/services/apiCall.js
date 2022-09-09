import axios from "axios";

async function getAlbumColletion() {
  const options = {
    method: "GET",
    url: "http://localhost:3000/albums",
    params: { _expand: "artist" },
  };
  const result = await apiCall(options);
  
  return result;
}

async function getAllTracks(albumId) {
  const options = {
    method: "GET",
    url: `http://localhost:3000/albums/${albumId}`,
    params: { _expand: "artist", _embed: "tracks" },
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

export { getAlbumColletion, getAllTracks };
