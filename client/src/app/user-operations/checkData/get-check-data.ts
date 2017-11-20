import {CheckItems} from './check-items';
import {User} from './user';
import {Place} from "../placeData/place";

export interface GetCheckData {
  fiscalDocumentNumber: string;
  fiscalDriveNumber: string;
  fiscalSign: string;
  nds10: number;
  nds18: number;
  totalSum: number;
  dateTime: string;
  items: CheckItems[];
  user: User;
  selected: boolean;
  place: Place;
}
