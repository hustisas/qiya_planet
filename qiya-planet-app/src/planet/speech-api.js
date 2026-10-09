import { postRequest } from "@/lib/smart-request.js";

export function assessSpeech(payload) {
  return postRequest("/qiya/speech/assess", payload);
}