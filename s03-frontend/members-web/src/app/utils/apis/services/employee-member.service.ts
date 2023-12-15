import { Injectable } from '@angular/core';
import { EMPLOYEE_MEMBERS, EMPLOYEE_MEMBER_DTO, Member, MemberSearch } from '../model/sample-data';
import { of } from 'rxjs';
import { generate } from '../model/id-generator';

@Injectable({
  providedIn: 'root'
})
export class EmployeeMemberService {

  private _members: Member[] = EMPLOYEE_MEMBERS

  constructor() {}

  save(member: Member) {
    const {id, ...data} = member
    if(id == 0)
      return this.add(member)

    return this.update(data, id as number)
  }

  add(member: Member) {
    member.id = generate(this._members) + 1
    this._members.push(member)
    return of(this._members[this._members.length - 1])
  }

  update(member: Member, id: number) {
    let index = this._members.findIndex(mem => mem.id == id)
    this._members[index] = member
    member.id = id
    return of(this._members[index])
  }

  search(params: MemberSearch) {
    if(params.keyword) {
      return of(this._members.filter(member => (member.name.startsWith(params.keyword)) || (member.email.startsWith(params.keyword)) || member.gender == params.keyword))
    }
    return of(this._members)
  }

  findById(id: number) {
    return of(EMPLOYEE_MEMBER_DTO.filter(dto => dto.profile.id == id).pop())
  }

}
