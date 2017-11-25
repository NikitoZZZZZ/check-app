import {Coords} from "./coords";

export interface Place {
  id: string;
  name: string;
  address: string;
  coords: Coords;
  rating: number;
  numOfChecks: number;
}
