import { Injectable } from '@angular/core';
import { STATES_DATA } from '../model/sample-data';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private _states = STATES_DATA

  constructor() { }

  getAllStates() {
    return of(this._states)
  }
}
